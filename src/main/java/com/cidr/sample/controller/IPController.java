package com.cidr.sample.controller;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cidr.sample.CIDRRepository;
import com.cidr.sample.model.IPAddress;


@RestController
public class IPController {
	@Autowired
	CIDRRepository repository;
	
	@GetMapping("/ipList")
	 	public ResponseEntity<Object> getIPList(){
	 		System.out.println("in get");
	 		
	 		List<IPAddress> ip_list = repository.findAllIP();
	 		
	 		return new ResponseEntity<Object>(ip_list, HttpStatus.OK);
	 	}
	 
	 @PutMapping("/acquire")
	 	public ResponseEntity<String> acquireIP(@RequestParam String ipAddress){
	 		System.out.println("in acquireIP" + ipAddress);
	 		
	 		int returnValue = repository.updateStatus(ipAddress, "acquired");
	 		
	 		return new ResponseEntity<String>("IP address acquired", HttpStatus.OK);
	 	}
	 
	 @PutMapping("/release")
	 public ResponseEntity<String> releaseIP(@RequestParam String ipAddress){
		 System.out.println("in releaseIP" + ipAddress);
	 		
	 		int returnValue = repository.updateStatus(ipAddress, "available");
	 		
	 		return new ResponseEntity<String>("IP address released", HttpStatus.OK);
	 }

}
