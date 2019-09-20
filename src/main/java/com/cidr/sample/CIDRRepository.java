package com.cidr.sample;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cidr.sample.model.IPAddress;

@Repository
public class CIDRRepository {

		@Autowired
		JdbcTemplate jdbcTemplate;

		public int insertCIDR(String cidr) {
			System.out.println("in insert");
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			String insert_sql = "insert into cidrs (cidr) values (?) ";
		
		  jdbcTemplate.update(connection -> { PreparedStatement ps = connection
		  .prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS); ps.setString(1, cidr); return ps; },
		  keyHolder);
		 
			
		/*
		 * jdbcTemplate.update(new PreparedStatementCreator() {
		 * 
		 * @Override public PreparedStatement createPreparedStatement(Connection con)
		 * throws SQLException { PreparedStatement statement =
		 * con.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);
		 * statement.setString(1, cidr);
		 * 
		 * return statement; } }, keyHolder);
		 */
			return (int) keyHolder.getKey();
			
			
		}
		
		public int[][] batchInsertIP(String[] addresses, int c_id, int batchSize) {

		        int[][] updateCounts = jdbcTemplate.batchUpdate(
		                "insert into ip_list (cidr_id, ipaddress, status) values(?,?,'available')",
		                Arrays.asList(addresses),
		                batchSize,
		                
		                new ParameterizedPreparedStatementSetter<String>() {
		                    public void setValues(PreparedStatement ps, String address) 
								throws SQLException {
		                        ps.setString(2, address);
		                        ps.setInt(1, c_id);
		                    }
		                });
		        return updateCounts;

		    }
		
		public List<IPAddress>findAllIP()
		{
			String sql = "SELECT * FROM IP_LIST";

			List<IPAddress> ipAddresses = jdbcTemplate.query(
	                sql,
	                new BeanPropertyRowMapper(IPAddress.class));

	        return ipAddresses;
		
		}
		
		public int updateStatus(String ip, String status)
		{
			String upateSql = "update  IP_LIST set status = ? where ipaddress=?";
			
			int returnValue =  jdbcTemplate.update(upateSql, status, ip);
			
			return returnValue;
			
		}

}
