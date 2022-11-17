import React from "react";
import CourseTitle from "./CourseTitle";
import Header from "../Home/Header";
import TimeMaterials from "./TimeMaterials";
export default function UpdateCourse(){




    return(

        <>

            <Header />
            <h3>Update Course</h3>

            <form className="create">
                <CourseTitle />
                <TimeMaterials />
   
                <section className="buttonSection">
                    <button className="create-btn">Update Course</button>
                    <button className="cancel-btn">Cancel</button>
                </section>
        
             </form>
        
        </>
    )
}