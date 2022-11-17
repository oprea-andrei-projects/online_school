import React from "react";
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { useNavigate } from "react-router-dom";

import image from "../../images/unu.jpg";
import Api from "../../Api";

export default function Book({book,handleRem}){

  let navigate = useNavigate();

 


    return(

        <>

              <Card style={{ width: '15rem', margin:"10px"}}>
                <Card.Img variant="top" src={image} className="bookImg" style={{ cursor:"pointer"}} onClick={()=>{navigate("/updateBook",{state:{id:book.id,title:book.title, created_at:book.created_at}})}}/>
                <Card.Body>
                  <Card.Title>{book.title}</Card.Title>
                  <Card.Text>
                    {book.created_at}
                  </Card.Text>
                  <Button variant="primary" onClick={()=>handleRem(book)}>Remove</Button>
                </Card.Body>
              </Card>

         
        
        </>
    )
}