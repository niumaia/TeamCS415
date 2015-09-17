<?php

class DB_Functions {

    private $db;

    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($u_name, $email, $password,$dob,$gender, $town, $country) {
        //$uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
       // $encrypted_password = $hash["encrypted"]; // encrypted password
       // $salt = $hash["salt"]; // salt
        $result = mysql_query("INSERT INTO users(u_name, u_gender, u_dob, u_town,u_country,u_email, u_pass)
 VALUES ('$u_name','$gender','$dob','$town','$country','$email','$hash')");
        // check for successful store
        if ($result) {
            // get user details 
            $uid = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT * FROM users WHERE u_id = $uid");
            // return user details
            return mysql_fetch_array($result);
        } else {
            return false;
        }
    }
	
	public function SaveBmi($u_id,$u_person,$u_height,$u_weight,$u_bmi,$category){
	
		$result = mysql_query("INSERT INTO bmi(u_id,u_person,u_height,u_weight,u_bmi,category) VALUES('$u_id','$u_person','$u_height','$u_weight','$u_bmi','$category')");
		if ($result) {
            // user existed 
            return $result;
        } else {
            // user not existed
            return false;
        }

	} 
	
	//***********left to edit **********
	
	public function updateUser($uid, $fname,$lname, $email, $town, $country,$postcode,$mobile) {
       
        $result = mysql_query("UPDATE user SET user_first_name='$fname',user_last_name='$lname', user_email='$email', user_town='$town', user_country='$country',user_post_code='$postcode',user_mobile='$mobile' WHERE user_id='$uid';");
        // check for successful store
        if ($result) {
            // get user details 
            
            $result = mysql_query("SELECT * FROM user WHERE user_id = '$uid'");
            // return user details
            return mysql_fetch_array($result);
        } else {
            return false;
        }
    }
	/* Applys for renewal*/
	public function ApplyforRenewal($user_id,$renewalApplied,$reciept){
		
			
			$result = mysql_query("UPDATE user SET user_renewal_applied='$renewalApplied', user_receipt='$reciept' WHERE user_id='$user_id';");
			//$rs = $this->execute_query($qry);
			

			if($result){
				return true;
			}
			return false;
			
			
			
		}
	

    /**
     * Get user by email and password
	 *********************&&&&&&&&*********
	 //edited
     */
    public function getUserByEmailAndPassword($user_id, $password) {
        $result = mysql_query("SELECT * FROM users WHERE user_id = '$user_id'") or die(mysql_error());
        // check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
            
            $u_pass = $result['user_password'];
            $hash = $this->hashSSHA($password);
		   //$hash = $password;
            // check for password equality
            if ($u_pass == $hash) {
                // user authentication details are correct
                return $result;
            }
        } else {
            // user not found
            return false;
        }
    }
	
	//get blood type test data
	public function getBloodTypeData($user_id) {
        $result = mysql_query("select btr.typeresult_id , btr.typeresult_date,btr.typeresult_value,btt.type_desc  from bloodtype_result btr join blood_type_test btt on btr.type_id = btt.type_id join user u on u.user_id = btr.user_id where u.user_id = '$user_id'") or die(mysql_error());
        
		
		// check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // 
			$result = mysql_fetch_array($result);
			
			return $result;
        } else {
            // user not found
            return false;
        }

	}
	
//================================================================================================
//student books on loan
public function getStudentOnLoan($user_id)
{
	$result = mysql_query ("SELECT b.bk_title, iss.issueDueDate, bc.bkCatNum FROM book b inner join bookcopy bc ON b.bk_id = bc.bk_id inner join issue iss ON iss.bkCopyID = bc.bkCopyID WHERE iss.StudentID = '$user_id' AND bc.copyStat_id = 2")or die(mysql_error());
	
	// check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // 
			$result = mysql_fetch_array($result);
			
			return $result;
        } else {
            // user not found
            return false;
        }
}
//================================================================================================

	//forget password
	//get user forget password details by email
	public function getUserForgetDetailsByEmail($email) {
        $result = mysql_query("SELECT * FROM user WHERE user_email = '$email'") or die(mysql_error());
        // check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
     
            if ($result) {
                // user authentication details are correct
                return $result;
            }
        } else {
            // user not found
            return false;
        }
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $result = mysql_query("SELECT user_email from user WHERE user_email = '$email'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed 
            return true;
        } else {
            // user not existed
            return false;
        }
    }
	
	public function isUserNewEmailAvail($email, $uid) {
        $result = mysql_query("SELECT user_email from user WHERE user_email = '$email' AND user_id!='$uid'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed 
            return true;
        } else {
            // user not existed
            return false;
        }
    }
	
	public function updatePassword($uid, $new) {
        
		$hash = $this->hashSSHA($new);
        //$user_password = $hash["user_password"]; // encrypted password
      
        $result = mysql_query("UPDATE user SET user_password='$hash' WHERE user_id='$uid';");
        // check for successful store
        if ($result) {
            // get user details 
            
            $result = mysql_query("SELECT * FROM user WHERE user_id = '$uid'");
            // return user details
            return mysql_fetch_array($result);
        } else {
            return false;
        }
    }
	
	public function resetpassword($email) {
        
		$hash = $this->hashSSHA("w45u8fk9");
        $result = mysql_query("UPDATE user SET user_password='$hash' WHERE user_email='$email';");
        // check for successful store
         //$result = mysql_fetch_array($result);
		
		if ($result) {
		
            // get user details 
            $result = mysql_query("SELECT user_password FROM user WHERE user_email = '$email'");
			
            return mysql_fetch_array($result);
        } else {
            return false;
        }
		

    }
	public function isOldPasswordCorrect($old, $uid) {
        $result = mysql_query("SELECT * from user WHERE user_id='$uid'");
        $no_of_rows = mysql_num_rows($result);
		
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
			
			$user_password = $result['user_password'];
            $hash = $this->hashSSHA($old);
           
         
            // check for password equality
            if ($user_password == $hash) {
                // user authentication details are correct
                return true;
            }else{
				
				return false;
			}
        } else {
            // user not existed
            return false;
        }
    }
	
	
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {

       
        
       // $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        //$hash = array("salt" => $salt, "encrypted" => $encrypted);
        $hash = md5($password);
		
		return $hash;
    }

    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    

}

?>
