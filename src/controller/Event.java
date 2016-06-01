package controller;

public interface Event {
  
  /**
   * @brief Callback function. 
   * @param a_obj Object to pass to receiving end.
   * @param a_arg 
   */
  public void signal(Object a_obj, Object a_arg);

}// end of interface Event
