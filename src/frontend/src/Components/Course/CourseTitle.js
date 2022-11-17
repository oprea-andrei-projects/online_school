import React, { useEffect } from "react";
import  {useState} from "react";

export default function CourseTitle({setTheCurs}){

    let[aCourse, setACourse]=useState({name:" ", department:" "});

   



    let handleChange = (e)=>{

        let obj = e.target;

        if(obj.classList.contains("title")){

           
            let ceva =aCourse;

            ceva.name=obj.value;

            setACourse(ceva);
        }

        if(obj.classList.contains("department")){

            let ceva =aCourse;

            ceva.department=obj.value;

            setACourse(ceva);


        }

        console.log(aCourse);

        setTheCurs(aCourse);
       
       
    }


    return(
        <>

        <div className="box1" onChange={handleChange}>
           
          
           <label for="staticEmail" class="form-label">Course Title</label>
          
            <input type="text" className="title" />
            
          
           <label for="exampleTextarea" class="form-label">Course Department</label>
            
            <input type="text" className="department" />
            

          
       </div>
        
        
        
        
        </>
    )
}