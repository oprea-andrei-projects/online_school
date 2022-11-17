import React, { useState } from "react";
import Api from "../../Api";
import CourseTitle from "./CourseTitle";
import Header from "../Home/Header";


export default function CreateCourse(){


    let[curs, setCurs] = useState({})

    let handleCourseCreation = async ()=>{

        let api = new Api();

        let x = await api.addACourse(curs);

        console.log(curs);

    }




    return(
        <>
            <Header />
            <h3>Create Course</h3>

            <form className="create">
                <CourseTitle setTheCurs={setCurs}/>
              
   
                <section className="buttonSection">
                    <button className="create-btn" onClick={handleCourseCreation}>Create Course</button>
                    <button className="cancel-btn">Cancel</button>
                </section>
        
             </form>
        
        
        
        </>
    )
}