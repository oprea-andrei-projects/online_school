import React from "react";
import Header from "../Home/Header";
import CreateBookForm from "./CreateBookForm";
import Api from "../../Api";
import { useState } from "react";
import { useNavigate } from "react-router-dom";


export default function AddBook(){

    let[book, setBook]=useState({});

    let navigate = useNavigate();

    let handleBookAdd = async (e)=>{

        e.preventDefault();
        let api = new Api();

        await api.addDasBook(1,book);
    }

    let handleCancel = ()=>{

        // navigate("/bookLib/1")
    }

    return(
        <>
           <Header />
            <h3 className="addB">Add Book</h3>

            <form className="create">
               <CreateBookForm setABook={setBook}/>
              
   
                <section className="buttonSection">
                    <button className="create-btn" onClick={handleBookAdd}>Add Book</button>
                    <button className="cancel-btn" onClick={handleCancel}>Cancel</button>
                </section>
        
            </form>
        
        </>
    )   
}