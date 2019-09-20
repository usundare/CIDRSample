package com.cidr.sample.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cidr.sample.CIDRRepository;


@RestController
//@RequestMapping("cidr")
public class CIDRController {
	@Autowired
	CIDRRepository repository;
	
	 @RequestMapping(value="/cidr", method = RequestMethod.POST)
	 public ResponseEntity<String> storeCIDR(@RequestParam String cidrAddress)
	 {
		 
	      //check if valid cidr pattern
		 Pattern pattern = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\\/(\\d|[1-2]\\d|3[0-2]))?$");
		// Matcher matcher = pattern.matcher("84.240.40.0/24");
		 Matcher matcher = pattern.matcher(cidrAddress);
		 if (!matcher.find()) {
			 return ResponseEntity.badRequest()
			            .body("Invalid CIDR");
		 }
		 
		 int cidr_id = repository.insertCIDR(cidrAddress);
		  //convert cidr to ip address range - IP v4, including network and boardcast.
		 SubnetUtils utils = new SubnetUtils(cidrAddress);
		 utils.setInclusiveHostCount(true);

		 SubnetInfo info = utils.getInfo();
		 
		 System.out.println("count: " + info.getAddressCount());
		 System.out.println("boadcast " + info.getBroadcastAddress());
		 System.out.println("network " + info.getNetworkAddress());
		 String[] addresses = info.getAllAddresses();
		
		 //Add more validations so that duplicate ip addresses are not added, skipping this for
		 // this exercise
		 repository.batchInsertIP(addresses, cidr_id, 1024);
		 
		 return new ResponseEntity<String>("IP range created", HttpStatus.OK);
	    }

	
}
