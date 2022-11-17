import React, {useState} from "react";
import Card from "../Course/Card";
import { useNavigate } from "react-router-dom"
import Alert from 'react-bootstrap/Alert';



export default function Main({courseList, mesaj}){

let navigate = useNavigate();

let handleCreateCourse = ()=>{

    navigate("/create")
}


return(

    
 <section className="cardSection">

    <div className="cutie newCourse" onClick={handleCreateCourse}>

        <p className="hAddCourse">+New Course</p>

    </div>


    {
        courseList.length == 0
        ?
        (
            <Alert className="h-100" variant={'warning'}>
                {mesaj}
            </Alert>
        )
        :
        (
            courseList.map(e=>{ 

                return <Card card={e} />
            })
        )

    }
    
      
    
    
    </section>
)




}