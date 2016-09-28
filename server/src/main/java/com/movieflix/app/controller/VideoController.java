package com.movieflix.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.movieflix.app.entity.Video;
import com.movieflix.app.service.VideoService;

/**
 * 
 * @author Loukik
 *
 *         This controller handles all Video related requests
 */
@RestController
@RequestMapping(value = "/videos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VideoController {

	@Autowired
	VideoService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Video> getAllVideo() {
		return service.getAllVideos();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Video getVideo(@PathVariable("id") String videoId) {
		return service.getVideo(videoId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/few/{count}")
	public List<Video> getFewVideos(@PathVariable("count") Integer count) {
		return service.getFewVideos(count);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Video addVideo(@RequestBody Video video) {

		return service.addVideo(video);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/all", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void addAllVideos(@RequestBody List<Video> videos) {

		service.addAllVideo(videos);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Video update(@PathVariable("id") String videoId, @RequestBody Video video) {
		return service.update(videoId, video);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable("id") String videoId) {
		service.delete(videoId);
	}
}
