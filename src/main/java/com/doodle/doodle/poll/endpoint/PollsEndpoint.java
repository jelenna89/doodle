package com.doodle.doodle.poll.endpoint;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doodle.doodle.poll.PollRepository;


@RestController
public class PollsEndpoint {

	@Autowired
	private PollRepository pollRepository;
	
	@RequestMapping(value = "/users/{user}/polls", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<String> retrieveAllUsersPolls(@PathVariable("user") String user) {
		if(user != null && !user.isEmpty()) {
			return pollRepository.findAll(user).stream().map(p->p.getPoll()).collect(Collectors.toList());
		}
		return pollRepository.findAll().stream().map(p->p.getPoll()).collect(Collectors.toList());
	}
	
	
	
	@RequestMapping(value = "/users/polls", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<String> retrieveAllPolls(@RequestParam(required = false) String text ,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date from,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date to,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date since,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date until) {
		
		
		if (text != null && !text.isEmpty()) {
			return pollRepository.findAll().stream().map(p->p.getPoll()).filter(p->p.contains(text)) .collect(Collectors.toList());
		}
		if (since != null) {
			return pollRepository.findAllSince(since).stream().map(p->p.getPoll()).collect(Collectors.toList());
		}

		if (until != null) {
			return pollRepository.findAllUntil(until).stream().map(p->p.getPoll()).collect(Collectors.toList());
		}

		if (from != null && to != null) {
			return pollRepository.findAllBetween(from,to).stream().map(p->p.getPoll()).collect(Collectors.toList());
		}

		return pollRepository.findAll().stream().map(p -> p.getPoll()).collect(Collectors.toList());
	}
	
}
