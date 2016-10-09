package com.movieflix.app.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieflix.app.entity.Video;
import com.movieflix.app.exceptions.EntityAlreadyExistsException;
import com.movieflix.app.exceptions.EntityNotFoundException;
import com.movieflix.app.repository.VideoRepository;

@Service
public class VideoServiceImplem implements VideoService {

	@Autowired
	private VideoRepository repository;

	@Override
	public List<Video> getAllVideos() {
		return repository.getAllVideos();
	}

	@Override
	public Video getVideo(String videoId) {
		Video video = repository.getVideo(videoId);
		if (video == null)
			throw new EntityNotFoundException("Video not found");

		return video;

	}

	@Override
	@Transactional
	public Video update(String videoId, Video video) {
		Video v = repository.getVideo(videoId);
		if (v == null)
			throw new EntityNotFoundException("User not found");

		return repository.update(video);
	}

	@Override
	@Transactional
	public Video addVideo(Video video) {
		Video v = repository.getVideoByTitleYear(video.getTitle(),video.getYear());
		if (v != null) {
			throw new EntityAlreadyExistsException("Video already exists");
		}

		return repository.create(video);
	}

	@Override
	@Transactional
	public void delete(String videoId) {
		Video video = repository.getVideo(videoId);
		if (video == null)
			throw new EntityNotFoundException("Video does not exist");
		repository.delete(video);


	}

	@Override
	@Transactional
	public void addAllVideo(List<Video> videos) {
		for(Video v:videos)
		{
			addVideo(v);
		}
	}

	@Override
	public List<Video> getFewVideos(Integer count) {
		List<Video> allVideos = repository.getAllVideos();
		if (allVideos != null && allVideos.size() > count) {
			int index = new Random().nextInt(allVideos.size() - count);
			return allVideos.subList(index, index + count);
		}
		return allVideos;
	}
}
