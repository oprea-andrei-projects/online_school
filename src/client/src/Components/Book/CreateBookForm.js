import React, { useState } from "react";

export default function CreateBookForm({setABook}){

    let[theBook, setTheBook]=useState({title:" ", created_at:" "})

    let handleChange = (e)=>{

        let obj = e.target;

        if(obj.classList.contains("title")){

            let ceva = theBook;
            ceva.title = obj.value;

            setTheBook(ceva);
        }

        if(obj.classList.contains("dateAdd")){

            let ceva = theBook;
            ceva.created_at = obj.value;

            setTheBook(ceva);

            
        }

        setABook(theBook);


    }


    return(

        <>
            <div className="box1" onChange={handleChange}>
           
          
                <label for="staticEmail" className="form-label">Book Title</label>
                
                    <input type="text" className="title" />
                    
                <label for="exampleTextarea" className="form-label">Added at</label>
                    
                    <input type="text" className="dateAdd" />
                    
          
            </div>
        
        
        
        
        </>
    )
}