import React, { useEffect, useState } from "react";
import CourseTitle from "./CourseTitle";
import Header from "../Home/Header";
import {  useParams } from 'react-router-dom';
import Api from "../../Api";



export default function CourseDetailsStd(){


    let[course, setCourse] = useState({});

  

    useEffect(()=>{

        getTheCourseByID()

    },[])

    let {id}=useParams();

    let getTheCourseByID = async ()=>{

        let api = new Api();
        let x = await api.findDasCourseByID(id);
        setCourse(x);
    }





    return(
        <>
            <Header />
            
            <section className="details">

            <h1>Course Detail</h1>

            <div className="course-detail-title">

                <h4>{course.name}</h4>
                
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