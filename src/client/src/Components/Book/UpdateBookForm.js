import React, { useState } from "react";

export default function UpdateBookForm({titlu, date, no, setABook}){

    let[theBook, setTheBook]=useState({id:no ,title:titlu, created_at:date})

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

        setABook(theBook)

       


    }


    return(

        <>
            <div className="box1" onChange={handleChange}>
           
          
                <label for="staticEmail" className="form-label">Book Title</label>
                
                    <input type="text" className="title" defaultValue={titlu}/>
                    
                <label for="exampleTextarea" className="form-label">Added at</label>
                    
                    <input type="text" className="dateAdd" defaultValue={date}/>
                    
          
            </div>
        
        
        
        
        </>
    )
}