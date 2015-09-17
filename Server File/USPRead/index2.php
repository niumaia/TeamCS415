<?php

/**
 * File to handle all API requests
 * Accepts GET and POST
 * 
 * Each request will be identified by TAG
 * Response will be JSON data

  /**
 * check for POST request
 Alvindra Dutt 3/5/15 CS424
 
 */

if (isset($_POST['tag']) && $_POST['tag'] != '') {
	
	// get tag
    $tag = $_POST['tag'];
	
	 // include db handler
    require_once 'include/DB_Functions.php';
	$db = new DB_Functions();
	
	 // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
	
	//for login
	if ($tag == 'login'){
		// Request type is check Login
        $user_id = $_POST['user_id'];
        $password = $_POST['user_password'];
		
		
        // check for user
        $user = $db->getUserByEmailAndPassword($user_id,$password );
		//$event = $event_db->getEventDetails();
		
		if ($user !=false){
			// user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["user_id"] = $user["user_id"];
			$response["user"]["user_fname"] = $user["user_fname"];
			$response["user"]["user_lname"] = $user["user_lname"];
			$response["user"]["user_dob"] = $user["user_dob"];
			$response["user"]["user_gender"] = $user["user_gender"];
            $response["user"]["user_address"] = $user["user_address"];
			$response["user"]["user_contact"] = $user["user_contact"];
			$response["user"]["user_email"] = $user["user_email"];	
			$response["user"]["user_role"] = $user["user_role"];			
            echo json_encode($response);
		} else {
			// user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect email or password!";
            echo json_encode($response);			
		}
		 
	}else if ($tag == 'blood_type_test'){
		$blood_type = $_POST['blood_type'];	
		$user_id = $_POST['user_id'];
		
		$user = $db->getBloodTypeData($user_id);
		
		
		//select distinct  u.user_name , b.description , r.test_id , b.blood_type from result r join blood_type b on r.blood_id = b.blood_id join user u on u.user_id = r.user_id where u.user_id = "120075274"	if($user){
		//TO DO dbfunctions 
		if ($user !=false){
			
			$response["success"] = 1;
			//$response["result_id"] = $user["result_id"];
			$response["bloodtype_result"]["typeresult_id"] = $user["typeresult_id"];
			$response["bloodtype_result"]["typeresult_date"] = $user["typeresult_date"];
			$response["bloodtype_result"]["typeresult_value"] = $user["typeresult_value"];
			$response["bloodtype_result"]["type_desc"] = $user["type_desc"];
			
			
			
			echo json_encode($response);
		}else{
			
            $response["error"] = 1;
            $response["error_msg"] = "Result Not Found!";
            echo json_encode($response);
		}
	}
	else if($tag == 'stud_onloan'){
		$issue = $_POST['issue'];
		$user_id = $_POST['user_id'];
		$user = $db->getStudentOnLoan($user_id);
		
		if($user != false)
		{
			$response["success"] = 1;
			$response["issue"] ["bk_title"] = $user["bk_title"];
			$response["issue"] ["issueDueDate"] = $user["issueDueDate"];
			$response["issue"] ["bkCatNum"] = $user["bkCatNum"];
		
			echo json_encode($response);
		}
		else{
			$response["error"] = 1;
			$response["error_msg"] = "Result Not Found!";
			echo json_encode($response);
		}
		
		
		
	
	}
	else if ($tag == 'student_onloan') {
		$user_id = $_POST['user_id'];
		$response = array();
			$response["loan"] = array();
			$response["success"] = 1;
			$result = mysql_query("SELECT b.bk_title, iss.issueDueDate, bc.bkCatNum FROM book b inner join bookcopy bc ON b.bk_id = bc.bk_id inner join issue iss ON iss.bkCopyID = bc.bkCopyID WHERE iss.StudentID = '$user_id' AND bc.copyStat_id = 2"); // Select all rows from fixture table
			while($row = mysql_fetch_array($result)){
				$tmp = array();        // temporary array to create single match information
				 
				$tmp["bk_title"] = $row["bk_title"];
				$tmp["issueDueDate"] = $row["issueDueDate"];
				$tmp["bkCatNum"] = $row["bkCatNum"];
				
				
		
				array_push($response["loan"], $tmp);
			}
			
			echo json_encode($response);
	
	}
	
	else if ($tag == 'video') {
		//$video = $_POST['video'];
		$response = array();
			$response["video"] = array();
			$response["success"] = 1;
			$result = mysql_query("SELECT b.bk_title, iss.issueDueDate, bc.bkCatNum FROM book b inner join bookcopy bc ON b.bk_id = bc.bk_id inner join issue iss ON iss.bkCopyID = bc.bkCopyID WHERE iss.StudentID = '$user_id' AND bc.copyStat_id = 2"); // Select all rows from fixture table
			while($row = mysql_fetch_array($result)){
				$tmp = array();        // temporary array to create single match information
				 
				$tmp["bk_title"] = $row["bk_title"];
				$tmp["issueDueDate"] = $row["issueDueDate"];
				$tmp["bkCatNum"] = $row["bkCatNum"]; 

		
				array_push($response["video"], $tmp);
			}
			
			echo json_encode($response);
	
	}
	
	else if ($tag == 'forget'){
		$userid = $_POST['user_id'];
		$email = $_POST['user_email'];
		
		if ($db->isUserExisted($email))
		{
			$user = $db->getUserForgetDetailsByEmail($email);
            if ($user) {
                // user response successfully
                $response["success"] = 1;
					$response["user"]["user_fname"] = $user["user_fname"];
					$response["user"]["user_lname"] = $user["user_lname"];
					$response["user"]["user_id"] = $user["user_id"];
					
                echo json_encode($response);	
            }
			
		}
		else 
		 {
            //error
			 $response["error"] = 1;
             $response["error_msg"] = "User is not registered in Software Foundation";
			  echo json_encode($response);
        }
		
	}
	else if ($tag == 'reset_password'){
		$userid = $_POST['user_id'];
		$email = $_POST['user_email'];
		$fname = $_POST['user_fname'];
		
		$message = "Hello ".$fname.",\n\n";
		$message= $message."We have reset your password to w45u8fk9 \n\n";
		$message=$message."Best Wishes,\nBMS Team";
		$subject    = "Password Reset: Blood Monitoring System";
		$to =  $email;
		$from="do-not-reply@bmsfiji.org";
		$headers = "From:" . $from; 
		
		$user = $db->resetpassword($email);
		
		if ($user){
			 $response["success"] = 1;
			 $response["user"]["user_password"] = $user["user_password"];
			 mail($to,$subject,$message,$headers);
			 echo json_encode($response);
		}
		else{
			$response["error"] = 1;
             $response["error_msg"] = "Error occured in Reset";
             echo json_encode($response);
		}
	}
	
	
	
}else {
    echo "Access Denied";
}


$video = 'video';
		$response = array();
			$response["video"] = array();
			$response["success"] = 1;
			$result = mysql_query("SELECT * FROM video"); // Select all rows from fixture table
			while($row = mysql_fetch_array($result)){
				$tmp = array();        // temporary array to create single match information
				 
				$tmp["vid_id"] = $row["vid_id"];
				$tmp["vid_name"] = $row["vid_name"];
				$tmp["vid_date_add"] = $row["vid_date_add"]; 
				$tmp["vid_url"] = $row["vid_url"]; 
				$tmp["vid_url_id"] = $row["vid_url_id"];
				$tmp["vid_img"] = $row["vid_img"];
		
				array_push($response["video"], $tmp);
			}
			
			echo json_encode($response);
	




?>