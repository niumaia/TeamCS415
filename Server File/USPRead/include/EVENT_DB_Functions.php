<?php

class EVENT_DB_Functions {

    private $event_db;

    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->event_db = new DB_Connect();
        $this->event_db->connect();
    }

    // destructor
    function __destruct() {
        
    }
	
	//get events details
	public function getEventDetails(){
		
		$result = mysql_query("SELECT * FROM event");
		
		//$no_of_rows = mysql_num_rows($result);
		if ($result) {
            // user existed 
            return $result = mysql_fetch_array($result);
        } else {
            // user not existed
            return false;
        }
		
		
		
	
   
    }
	
	public function AttendEvent($userid,$eventid){
		
		$result = mysql_query("INSERT INTO event_user(user_id, event_id) VALUES('$userid', '$eventid')");
		
		//$no_of_rows = mysql_num_rows($result);
		if ($result) {
            // user existed 
            return $result;
        } else {
            // user not existed
            return false;
        }
		
		
		
	
   
    }

}

?>
