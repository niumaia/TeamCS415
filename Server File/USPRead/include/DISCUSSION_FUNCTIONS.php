<?php

class DISCUSSION_FUNCTIONS {

    private $dis_db;

    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->dis_db = new DB_Connect();
        $this->dis_db->connect();
    }

    // destructor
    function __destruct() {
        
    }
	
	public function StoreDiscussion($subject,$discussion,$user_id){
	
		$result = mysql_query("INSERT INTO discussion(dis_subject, discussion,dis_date,user_id) VALUES('$subject', '$discussion',NOW(),'$user_id')");
		if ($result) {
            // user existed 
            return $result;
        } else {
            // user not existed
            return false;
        }

	}
	
	public function ReplyDiscussion($reply,$dis_id,$user_id){
	
		$result = mysql_query("INSERT INTO dis_reply(reply,dis_id,reply_date,user_id) VALUES('$reply','$dis_id',NOW(),'$user_id')");
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
