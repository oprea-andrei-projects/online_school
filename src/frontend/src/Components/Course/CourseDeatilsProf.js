import React,{useEffect, useState} from "react";
import ButtonSection from "./ButtonSection";
import {  useParams } from 'react-router-dom';
import Header from "../Home/Header";
import Api from "../../Api";
import { useNavigate } from "react-router-dom";

export default function CourseDetailsProf(){

    let {id} = useParams();

    let navigate = useNavigate();

    let[course, setCourse]=useState({});

    

    useEffect(()=>{

        getTheCourseByID()


    },[])

    let getTheCourseByID = async ()=>{

        let api = new Api();

        let x = await api.findDasCourseByID(id);

        setCourse(x);

    }

    let deleteTheCourse = async()=>{

        let api = new Api();

        await api.deleteDasCourse(id);

        navigate("/")
    }

    let handleChange = (e)=>{

        let obj = e.target;

        if(obj.classList.contains("cTitle")){

            let ceva = course;
            ceva.name = obj.value;
            setCourse(ceva);
        }
    }

    let updateTheCourse = async ()=>{

        let api = new Api();

        await api.updateTheCourse(1,course);

    }


    return(
        <>

            <Header />

            <ButtonSection deleteCourse={deleteTheCourse} updateCourse={updateTheCourse}/>
            
            <section className="details">

            <h1>Course Detail</h1>

            <div className="course-detail-title" onChange={handleChange}>

                {/* <h4>{course.name}</h4> */}
                <input type="text" className="border-0 cTitle"  defaultValue={course.name} />
                
            </div>
            <p>By Andrei</p>

            <div className="course-detail-title">


            <h4>ESTIMATED TIME</h4>
            </div>
            <p>4 Hours</p>

            <div className="course-detail-title">

            <h4>MATERIALS NEEDED</h4>
            </div>
            <p>Laptop / tablet</p>  

            </section>
        
        
        
        
        
        </>
    )
}