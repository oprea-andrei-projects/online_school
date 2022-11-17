import React, { useEffect, useState } from "react"
import { BrowserRouter, Route, Routes } from "react-router-dom";
import CreateCourse from "./Course/CreateCourse";
import UpdateCourse from "./Course/UpdateCourse";
import Home from "./Home/Home";
import CourseDetailsStd from "./Course/CourseDetailsStd";
import Enrolment from "./Course/Enrolment";
import AddBook from "./Book/AddBook";
import BookLib from "./Book/BookLib";
import UpdateBook from "./Book/UpdateBook";
import CourseDetailsProf from "./Course/CourseDeatilsProf";


export default ()=>{

 

  return(


    <>

      <BrowserRouter>
      
        <Routes>

          <Route path="/" element={<Home />} />
          <Route path="/create" element={<CreateCourse />} />
          <Route path="/update" element={<UpdateCourse />} />
          <Route path="/enrolment/:id" element={<Enrolment />} />
          <Route path="/bookLib/:id" element={<BookLib />} />
          <Route path="/addBook" element={<AddBook />} />
          <Route path="/updateBook" element={<UpdateBook />} />
          <Route path="/courseDetailsProf/:id" element={<CourseDetailsProf />} />
          </Routes>
         
      </BrowserRouter>

      

    </>
  )
}
