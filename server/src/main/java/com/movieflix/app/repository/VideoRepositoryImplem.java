package com.movieflix.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.movieflix.app.entity.Video;
/**
 * 
 * @author Loukik
 *
 *	This repository class connects to the Video table in the database
 */
@Repository
public class VideoRepositoryImplem implements VideoRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Video> getAllVideos() {
		TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
		List<Video> videos = query.getResultList();
		return videos;
	}

	@Override
	public Video getVideo(String videoId) {
		return em.find(Video.class, videoId);
	}

	@Override
	public Video update(Video video) {
		return em.merge(video);
	}

	@Override
	public void delete(Video video) {
		em.remove(video);

	}

	@Override
	public Video create(Video video) {
		em.persist(video);
		return video;
	}

	@Override
	public Video getVideoByTitleYear(String title, int year) {
		TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v WHERE v.title=:ptitle AND v.year=:pyear ", Video.class);
		query.setParameter("ptitle", title);
		query.setParameter("pyear", year);
		List<Video> videos = query.getResultList();
		if (videos.size() == 1)
			return videos.get(0);
		else
			return null;
	}

}
