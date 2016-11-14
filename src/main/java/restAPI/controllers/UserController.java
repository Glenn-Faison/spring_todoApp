package restAPI.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restAPI.models.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private List<User> allUsers;

    private void editUserInfo(User user1, final User user2) {
        user1.setFirstName(user2.getFirstName());
        user1.setLastName(user2.getLastName());
        user1.setEmail(user2.getEmail());
        user1.setPassword(user2.getPassword());
    }

    private String getNewId() {
        if (this.allUsers.isEmpty()) {
            return "1";
        }
        String lastIdStr = this.allUsers.get(this.allUsers.size() - 1).getId();
        int newIdInt = Integer.parseInt(lastIdStr);
        return Integer.toString(newIdInt + 1);
    }

    public UserController() {
        this.allUsers = new ArrayList<>();
        User u1 = new User("Glenn", "Faison", "glennfaison@gmail.com", "11111");
        User u2 = new User("Bill", "Acha", "achabill12@gmail.com", "11111");
        User u3 = new User("Anta", "Mbunwe", "antatiayon@gmail.com", "11111");
        u1.setId(getNewId());
        this.allUsers.add(u1);
        u2.setId(getNewId());
        this.allUsers.add(u2);
        u3.setId(getNewId());
        this.allUsers.add(u3);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(value = "name", defaultValue = "") String name) {
        List<User> returnList = new ArrayList<>(this.allUsers);
        name = name.toLowerCase();
        if (name == "") {
            return returnList;
        }
        for (int i = 0; i < returnList.size(); i++) {
            if (!returnList.get(i).getFullName().contains(name)) {
                returnList.remove(i);
                i--;
            }
        }
        return returnList;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addUsers(@RequestBody List<User> newUsers) {
        int usersCreated = 0;
        for (User newUser : newUsers) {
            int count = 0;
            for (int i = 0; i < this.allUsers.size(); i++) {
                if (!this.allUsers.get(i).is(newUser)) {
                    count++;
                }
            }
            if (count == this.allUsers.size()){
                newUser.setId(getNewId());
                this.allUsers.add(newUser);
                usersCreated++;
            }
        }
        return new ResponseEntity<String>("\n" + usersCreated + " Users Created\n", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") String id) {
        User returnValue = null;
        for (User user : this.allUsers) {
            if (user.getId().equals(id)) {
                returnValue = user;
                break;
            }
        }
        return new ResponseEntity<User>(returnValue, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> editUser(@PathVariable("id") String id, @RequestBody User newUser) {
        User returnValue = null;
        for (User user : this.allUsers) {
            if (user.getId().equals(id)) {
                editUserInfo(user, newUser);
                returnValue = user;
                break;
            }
        }
        return new ResponseEntity<User>(returnValue, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        for (User user : this.allUsers) {
            if (user.getId().equals(id)) {
                this.allUsers.remove(user);
                break;
            }
        }
        return new ResponseEntity<String>("\nUser Deleted\n", HttpStatus.NO_CONTENT);
    }
}
