package com.naver.projectserver.repo;

import java.util.List;

import com.naver.projectserver.mapper.Event;

public interface EventRepo {
	boolean insert(Event event);
	boolean update(Event event);
	boolean delete(String id);
	Event selectOne(String id);
	List<Event> selectAll(String owner);
}
