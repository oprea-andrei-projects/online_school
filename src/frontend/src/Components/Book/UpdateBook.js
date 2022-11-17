import React from "react";
import Header from "../Home/Header";
import Api from "../../Api";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import UpdateBookForm from "./UpdateBookForm";
import {useLocation} from 'react-router-dom';

export default function UpdateBook(){

    let[book, setBook]=useState({});
     

   
    let location = useLocation();
    let navigate = useNavigate();

    let[title, setTitle] =useState(location.state.title);
    let [created_at, setCreatedAt] = useState(location.state.created_at);
    let [id, setId] = useState(location.state.id);

    let handleBookUpdate = async (e)=>{

        e.preventDefault();
        let api = new Api();

        await api.updateDasBook(1, book);
    }

    let handleCancel = ()=>{

        navigate("/bookLib/1")
    }

    return(
        <>
           <Header />
            <h3 className="addB">Update Book</h3>

            <form className="create">
             
                <UpdateBookForm titlu={title} date={created_at} no={id} setABook={setBook}/>
              
   
                <section className="buttonSection">
                    <button className="create-btn" onClick={handleBookUpdate}>Update Book</button>
                    <button className="cancel-btn" onClick={handleCancel}>Cancel</button>
                </section>

            </form>
          
        
        </>
    )   
}