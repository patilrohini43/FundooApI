package com.bridgelabz.fundoo.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.stream.Stream;
import java.net.MalformedURLException;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.core.io.UrlResource;


import java.nio.file.Files;

import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.exception.UploadException;
import com.bridgelabz.fundoo.exception.UploadFileNotFoundException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.service.UserService;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;


@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins="http://localhost:4200")

public class UserController {
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;
	
	private Response response;
	
	static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	private final Path pathlocation=Paths.get("/home/admin1/Documents/workspace-sts-3.9.7.RELEASE/FundDooAPIData/src/main/resources/image");
	
	@RequestMapping("/user")
	public List<User> getAllUser()
	{
		return userService.getAll();
		
	}

	
//	@RequestMapping(method=RequestMethod.POST,value="/register")
//	public void addUser(@RequestBody UserDto userDto) throws UnsupportedEncodingException, UserException
//	
//	{
//		
//		userService.registerUser(userDto);
//	}
//	
	
	
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)	
	public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request)   

       {
		
	    if(bindingResult.hasErrors())
	    {
			logger.error("Error in Binding The User Details");
		 //  throw new UserException(201,environment.getProperty("data.message"));
			
	    }
		
			Response response= userService.registerUser(userDto);
			// System.out.println("successfully registered");
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	
		
	}

	@RequestMapping(value = "/Login", method = RequestMethod.PUT)
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto , BindingResult bindingResult) 
	{
		//System.out.println("Hello");

		{
		if(bindingResult.hasErrors()) {
			logger.error("Error in Binding The User Details");
		}
		
		Response response=userService.Login(loginDto);
	
		//httpresponse.addHeader("jwt_token", token);
	
		//response.setStatusCode(200);
		//response.setStatusMessage(environment.getProperty("message"));
	
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	}
	

	     
	
	@RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
	public ResponseEntity<Response> verifyEmail(@PathVariable String token) 
	{
		logger.info("User Verify");
		
		   Response response=userService.verifyToken(token);
			System.out.println("Verify Successfully");
	
			
		return new ResponseEntity<Response>(response,HttpStatus.OK);
  
}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ResponseEntity<Response> forgotPassword(@RequestParam("email") String email) 
	{
		logger.info("Forgot Password");
		
		Response response=userService.forgotPassword(email);
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	

	@RequestMapping(value = "/reset/{token}", method = RequestMethod.GET)
	public ResponseEntity<Response> resetPassword(@PathVariable String token,@RequestParam String password)
	{
		logger.info("resetPassword");
		
		Response response=userService.resetPassword(token, password);
		
		System.out.println("Reset SuccessFully");
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
	} 
	
	
	
	
	@RequestMapping(value = "user/profileupload", method = RequestMethod.PUT,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response> imageUploads(@RequestHeader(value="jwt_token") String token,@RequestParam("File") MultipartFile file) throws IOException
	{
		logger.info("uploading..");
		UUID uuid = UUID.randomUUID();
		
		System.out.println(uuid.toString());
		String uuidString = uuid.toString();
	
		//File convertFile=new File(this.pathlocation+"/"+file.getOriginalFilename());
		//System.out.println(convertFile);
       // convertFile.createNewFile();
		//FileOutputStream fout=new FileOutputStream(convertFile);
	//	fout.write(file.getBytes());
		//fout.close();
		 Files.copy(file.getInputStream(), this.pathlocation.resolve(uuidString),
                 StandardCopyOption.REPLACE_EXISTING);
		Response response=userService.imageUpload(token,uuid.toString());
		
		//Response response=Utility.statusResponse(401, environment.getProperty("note.upload.message"));
		
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
	} 
	
	
	
	
	   @GetMapping("/user/getProfile/{token}")
	    public Resource getImageAll(@PathVariable("token") String token) {
	    	
	    	long userId = UserToken.tokenVerify(token);
	    	String filename=userService.getImage(userId);
	    	
	    	System.out.println(filename);
	    	  Path file = this.pathlocation.resolve(filename);
	    	System.out.println(file);
	        try {
	          
	            Resource resource = new UrlResource(file.toUri());
	            if (resource.exists() || resource.isReadable()) {
	                return resource;
	            } else {
	                throw new UploadFileNotFoundException(
	                        "Could not read file: " + filename);

	            }
	        } catch (MalformedURLException e) {
	            throw new UploadFileNotFoundException("Could not read file: " + filename, e);
	        }
	    }
	   
	   
	   
	   

}



