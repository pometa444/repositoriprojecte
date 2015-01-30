<?php
 
class funciones_BD {
 
    private $db;
    private $con;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database

        $this->db = new DB_Connect();
        $this-> con = $this->db->connect();

    }
 
    // destructor
    function __destruct() {
 
    }
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($username, $password) {
   
    $result = mysqli_query($this-> con, "INSERT INTO usuaris(idusuari, nombre, passwd) VALUES(0,'$username', md5('$password'))");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }
 
 
     /**
     * Verificar si el usuario ya existe por el username
     */

    public function isuserexist($username) {

        $result = mysqli_query($this-> con, "SELECT nombre from usuaris WHERE nombre = '$username'");

        $num_rows = mysqli_num_rows($result); //numero de filas retornadas

        if ($num_rows > 0) {

            // el usuario existe 

            return true;
        } else {
            // no existe
            return false;
        }
    }
 
   
	public function login($user,$passw){

	$result=mysqli_query($this-> con, "SELECT COUNT(*) FROM usuaris WHERE nombre='$user' AND passwd=md5('$passw') "); 
	$count = mysqli_fetch_row($result);

	/*como el usuario debe ser unico cuenta el numero de ocurrencias con esos datos*/


		if ($count[0]==0){

		return true;

		}else{

		return false;

		}
	}
  public function idUsuari($user){

	$result=mysqli_query($this-> con, "SELECT idUsuari FROM usuaris WHERE nombre='$user' "); 
	$row = mysqli_fetch_row($result);
	
		return $row[0];
	
	}
}
 
?>
