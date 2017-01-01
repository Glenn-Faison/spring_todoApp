angular.module('todoApp', [])
.controller('TaskController', ['$http', function($http) {
    var self = this;
    self.taskList = [];
    self.selectedIndices = [];
    self.newTask = {
        completed: false,
        description: "",
        owner: {
            email: "",
            password: "",
            username: ""
        }
    };
    
    self.getTasks = function() {
        $http.get("http://localhost:8080/api/v1/tasks")
            .then(function(result) {
                self.taskListPage = result.data;
                self.taskList = self.taskListPage.content;
            });
    };

    self.getStyle = function(task) {
        if (task == null) {
            return 'isEven';
        }
        var index = self.taskList.indexOf(task);
        if (index % 2 == 0) {
            return 'isEven';
        }
        return 'isOdd';
    };

    self.addTask = function() {
        $http.get("http://localhost:8080/api/v1/users/" + self.newTask.owner.userId)
            .then(function(result) {
                self.newTask.owner = result.data;
                $http.post("http://localhost:8080/api/v1/tasks", self.newTask)
                    .then(self.getTasks);
            })
            .then(function() {
                self.newTask = {};
            });
    };

    self.selectTask = function(index) {
        self.selectedIndices[index] = !self.selectedIndices[index];
        console.log(self.selectedIndices[index]);
    };

    self.deleteTasks = function() {
        for (i in self.selectedIndices) {
            console.log(i);
            $http.delete("http://localhost:8080/api/v1/users/1/tasks/" + i)
                .then(self.getTasks);
        }
    };

    self.getTasks();
}]);