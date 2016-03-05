package controller;

public interface Event {
  
  /**
   * @brief Callback function. 
   * @param o Object to pass to receiving end.
   */
  public void signal(Object o); 

}// end of interface Event
