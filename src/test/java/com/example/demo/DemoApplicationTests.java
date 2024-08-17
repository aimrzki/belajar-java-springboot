package com.example.demo;

import com.example.demo.controller.TaskController;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	private TaskService taskService;

	@InjectMocks
	private TaskController taskController;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateTask() {
		// Arrange
		Task task = new Task();
		task.setDescription("Test Task");
		task.setSeverity(3);
		task.setAssignee("John Doe");
		task.setStoryPoint(5);

		Task savedTask = new Task();
		savedTask.setTaskId("12345");
		savedTask.setDescription("Test Task");
		savedTask.setSeverity(3);
		savedTask.setAssignee("John Doe");
		savedTask.setStoryPoint(5);

		when(taskService.addTask(any(Task.class))).thenReturn(savedTask);

		// Act
		Task result = taskController.createTask(task);

		// Assert
		assertEquals(savedTask.getTaskId(), result.getTaskId());
		assertEquals(savedTask.getDescription(), result.getDescription());
		assertEquals(savedTask.getSeverity(), result.getSeverity());
		assertEquals(savedTask.getAssignee(), result.getAssignee());
		assertEquals(savedTask.getStoryPoint(), result.getStoryPoint());
	}
}