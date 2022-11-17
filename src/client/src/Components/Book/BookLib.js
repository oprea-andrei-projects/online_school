import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Api from "../../Api";
import Book from "./Book";
import Header from "../Home/Header";
import { useNavigate } from "react-router-dom"
import Alert from 'react-bootstrap/Alert';

export default function BookLib(){

    let {id} = useParams();

    let [books, setBooks] = useState([]);

    let [msg, setMsg] = useState("");


    let navigate = useNavigate();

    useEffect(()=>{

        getAllTheBooks()

    },[])

    let getAllTheBooks = async ()=>{

        let api = new Api();

        let arr = await api.getUserBooks(1);

        if(typeof arr == "string"){

            setMsg(arr);

        }else{

            setBooks(arr);
        }

        console.log(arr);
        setBooks(arr);

    }

    let handleBookAdd = ()=>{


            navigate("/addBook");
        
    }

    let handleRemove = async (book)=>{

        let api = new Api();
    

        await api.deleteDasBook(1,book.id);
       


        setBooks(prev=>[
            ...prev.filter(b=>b.id!=book.id)
        ])


    
        
    
    
      }






    return(

        <>
            <Header />

            <div className="bookLayout">

                <div className="addBook" onClick={handleBookAdd}>
                  +
                </div>
            {
                msg !=""
                ?
                (
                    <Alert className="h-100 mt-auto" variant={'warning'}>
                            {msg}
                    </Alert>
                )
                :
                (
                    books.map(e=>{
                        return <Book book={e} handleRem={handleRemove} />
                    })
                )


            }
            
            </div>


        
        
        </>
    )
}