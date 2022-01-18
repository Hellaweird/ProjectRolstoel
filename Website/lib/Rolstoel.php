<?php


namespace DaanKoster;

class Rolstoel
{

    private $ds;

    function __construct()
    {
        require_once __DIR__ . '/../lib/DataBase.php';
        $this->ds = new DataBase();
    }

    public function getRolstoel()
    {
        $query = 'SELECT * FROM rolstoelen';
        $paramType = 's';
        $rolstoelRecord = $this->ds->select($query, $paramType);

        $rolstoelen = $rolstoelRecord;
        return $rolstoelRecord;
    }

    public function editRolstoel($id, $tag, $eigenschap)
    {
        $query = 'UPDATE rolstoelen SET tag = ?,eigenschap= ? WHERE id = ?';
        $paramType = 'sss';
        $paramValue = array(
            $tag,
            $eigenschap,
            $id
        );
        $this->ds->execute($query, $paramType, $paramValue);

    }

}