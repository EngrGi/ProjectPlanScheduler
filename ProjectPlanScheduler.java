import java.time.LocalDate;
import java.util.*;

// TaskBean to hold the information of each task
class TaskBean {
    String name;
    String dependencyName;
    int duration;
    int newStartDuration;
    int newEndDuration;
    Boolean isError = false;
    
    public TaskBean(String name, int duration) {
        this.name = name;
        this.duration = duration;
        
    }
    
    public int getDuration (){
        return this.duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public int getNewStartDuration (){
        return this.newStartDuration;
    }
    
    public void setNewStartDuration(int newStartDuration) {
        this.newStartDuration = newStartDuration;
    }
    
     public int getNewEndDuration (){
        return this.newEndDuration;
    }
    
    public void setNewEndDuration(int newEndDuration) {
        this.newEndDuration = newEndDuration;
    }
    
    public Boolean getIsError (){
        return this.isError;
    }
    
    public void setIsError(Boolean isError) {
        this.isError = isError;
    }
    
    public String getName (){
        return this.name;
    }
    
    

}

public class ProjectPlanScheduler
{
	public static void main(String[] args) {
		// Define tasks given the task name and days of duration
        TaskBean task1 = new TaskBean("Task 1", 5);
        TaskBean task2 = new TaskBean("Task 2", 3);
        TaskBean task3 = new TaskBean("Task 3", 4);
        TaskBean task4 = new TaskBean("Task 4", 2);
        
        //Add dependency for each task
        addDependency(task1, task2);
        addDependency(task2, task3);
        addDependency(task2, task4);
        
        
        List<TaskBean> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        
        getSchedule(taskList, LocalDate.of(2024, 9, 1));
	}
	
	// Process the dependency task
    public static void addDependency(TaskBean task1, TaskBean task2) {
      if (task1.getName() == task2.getName()) {
          task1.setIsError(true);
      } else if (task1.getNewStartDuration() == 0 && task2.getNewStartDuration() == 0) {
          task2.setNewStartDuration(task1.getDuration());
          task2.setNewEndDuration(task1.getDuration() + task2.getDuration());
      } else if (task2.getNewEndDuration() < task1.getDuration() + task2.getDuration()){
          task2.setNewStartDuration(task1.getNewEndDuration());
          task2.setNewEndDuration(task1.getNewEndDuration() + task2.getDuration());
      }
    }
    
    //Process the schedule for each task
    public static void getSchedule(List<TaskBean> taskList, LocalDate startDate) {
     
        for (TaskBean task : taskList) {
          if (!task.getIsError()) {
            if (task.getNewStartDuration() == 0 && !task.getIsError()) {
              System.out.println(task.getName() + " Start Date: " + startDate);
              System.out.println(task.getName() + " End Date: " + startDate.plusDays(task.getDuration() - 1) + "\n");
            } else {
              System.out.println(task.getName() + " Start Date: " + startDate.plusDays(task.getNewStartDuration() - 1));
              System.out.println(task.getName() + " End Date: " + startDate.plusDays(task.getNewEndDuration() - 1) + "\n");
            } 
          } else {
              System.out.println("Error! Input cannot have same task!\n");
              break;
          }
          
        }  
    }
	
	
	
}