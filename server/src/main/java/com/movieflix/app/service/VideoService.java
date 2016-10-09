package com.movieflix.app.service;

import java.util.List;

import com.movieflix.app.entity.Video;

public interface VideoService {

	List<Video> getAllVideos();

	Video getVideo(String videoId);

	Video update(String videoId, Video video);

	Video addVideo(Video video);

	void delete(String videoId);

	void addAllVideo(List<Video> videos);

	List<Video> getFewVideos(Integer count);

}
