package com.springapi.springRest.HomeController;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonView;
import com.springapi.springRest.Entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class HomeController {
	List<User> list = new LinkedList<>();
	public HomeController() {
		// TODO Auto-generated constructor stub
		list.add(new User(1,"karan","karan@gmail.com","male","active"));
		list.add(new User(2,"kartik","kartik@gmail.com","male","inactive"));
		list.add(new User(3,"divya","divya@gmail.com","female","active"));
		
	}
	
	@GetMapping(value = "/getMap",produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_XML_VALUE })
	public List<User> getUsers(){
//		System.out.println(list);
		return list;
	}
	
	@GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
    public ResponseEntity<List<User>> getUserEntity() {
//		System.out.println(list.get(0));
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
	
	@PostMapping(value = "/addUser", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public void addFormDataUser(@RequestParam MultiValueMap<String, String> formData) {
//		System.out.println(user);
		int id = Integer.parseInt(formData.get("id").get(0));
		String name = formData.get("name").get(0);
		String email = formData.get("email").get(0);
		String gender = formData.get("gender").get(0);
		String status = formData.get("status").get(0);
		User user = new User(id,name,email,gender,status);
//		System.out.println("newly Created"+user);
		list.add(user);
	}
	
	@PostMapping(value = "/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public void addObjectUser(@RequestBody User user) {
//		System.out.println(user);
		list.add(user);
		
	}
	
	@DeleteMapping(value = "/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		User delUser = null;
		for(User user :list) {
			if(user.getId() == id) {
				delUser = user;
				break;
			}
		}
		list.remove(delUser);
//		System.out.println(delUser);
	}
	
}

//@JsonView(View.class)
