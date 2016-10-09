package com.movieflix.app.repository;

import java.util.List;

import com.movieflix.app.entity.Video;

public interface VideoRepository {

	Video getVideo(String videoId);

	List<Video> getAllVideos();

	void delete(Video video);

	Video create(Video video);

	Video getVideoByTitleYear(String title, int year);

	Video update(Video video);

}
