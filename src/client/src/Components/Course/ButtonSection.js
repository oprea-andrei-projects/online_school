import React from "react";

export default function ButtonSection({deleteCourse, updateCourse}){





    return(
        

        <section className="buttonSection bs2">

            <button className="create-btn" onClick={updateCourse}>Update Course</button>
            <button className="create-btn" onClick={deleteCourse}>Delete Course</button>
            <button className="cancel-btn">Return to List</button>


        </section>
        
        
        
        
        
    )
}