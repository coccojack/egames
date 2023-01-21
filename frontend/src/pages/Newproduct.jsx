import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { Footer, Navbar } from "../components";

const Newproduct = () => {

    const [message, setMessage] = useState("");
    const navigate = useNavigate();
    let title, description, platformName, genre, price, stock;

    const handleSubmit = async (e) => {
        e.preventDefault();
        title = document.getElementById("title").value;
        description = document.getElementById("description").value;
        genre = document.getElementById("genre-name").value;
        platformName = document.getElementById("platform-name").value;
        price = document.getElementById("price").value;
        stock = document.getElementById("stock-quantity").value;
        console.log("titolo: " + title, " escizione: " + description + " geneRe" + genre + "platfomr" + platformName + " pRice" + price)
        try {
            let res = await fetch("http://localhost:8081/egames/videogame/add", {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                method: "POST",
                body: JSON.stringify({
                    title: title,
                    description: description,
                    genre: genre,
                    platformName: platformName,
                    price: price,
                    stockQuantity: stock
                })
            });
            let resJson = await res.json();
            if (res.status === 200) {
                setMessage("Videogame added successfully");
                navigate("/product/" + resJson.id)
            } else {
                setMessage("Some error occured");
            }
        } catch (err) { }
    }




    const ProductForm = () => {
        return (
            <>
                <div>
                    <div className="container py-5">
                        <div className="row my-4">
                            <div className="col-md-7 col-lg-8">
                                <div className="card mb-4">
                                    <div className="card-header py-3">
                                        <h4 className="mb-0">Videogame information:</h4>
                                    </div>
                                    <div className="card-body">
                                        <form className="needs-validation" validate="true" onSubmit={handleSubmit}>
                                            <div className="row g-3">
                                                <div className="col-sm-12 my-1">
                                                    <label for="title" className="form-label">
                                                        Title
                                                    </label>
                                                    <input
                                                        type="text"
                                                        className="form-control"
                                                        id="title"
                                                        placeholder=""
                                                        required
                                                    />
                                                    <div className="invalid-feedback">
                                                        Valid title is required.
                                                    </div>
                                                </div>

                                                <div className="col-12 my-1">
                                                    <label for="description" className="form-label">
                                                        Description
                                                    </label>
                                                    <input
                                                        type="textarea"
                                                        className="form-control"
                                                        id="description"
                                                        placeholder="Insert videogame descrition here..."
                                                        required
                                                    />
                                                    <div className="invalid-feedback">
                                                        Please enter a valid description.
                                                    </div>
                                                </div>
                                                <div className="col-6 my-1">
                                                    <label for="platform-name" className="form-label">
                                                        Platform Name
                                                    </label>
                                                    <input
                                                        type="textarea"
                                                        className="form-control"
                                                        id="platform-name"
                                                        placeholder=""
                                                        required
                                                    />
                                                    <div className="invalid-feedback">
                                                        Please enter a valid platform name.
                                                    </div>
                                                </div>
                                                <div className="col-6 my-1">
                                                    <label for="genre-name" className="form-label">
                                                        Genre Name
                                                    </label>
                                                    <input
                                                        type="textarea"
                                                        className="form-control"
                                                        id="genre-name"
                                                        placeholder=""
                                                        required
                                                    />
                                                    <div className="invalid-feedback">
                                                        Please enter a valid genre name.
                                                    </div>
                                                </div>

                                            </div>

                                            <hr className="my-4" />

                                            <h4 className="mb-3">Storage information</h4>

                                            <div className="row gy-3">
                                                <div className="col-md-6">
                                                    <label for="stock-quantity" className="form-label">
                                                        Stock quantity
                                                    </label>
                                                    <input
                                                        type="number"
                                                        min="0"
                                                        className="form-control"
                                                        id="stock-quantity"
                                                        placeholder=""
                                                        required
                                                    />
                                                    <div className="invalid-feedback">
                                                        Stock quantity is required
                                                    </div>
                                                </div>

                                                <div className="col-md-6">
                                                    <label for="price" className="form-label">
                                                        Price
                                                    </label>
                                                    <input
                                                        type="number"
                                                        min="0"
                                                        className="form-control"
                                                        id="price"
                                                        placeholder=""
                                                        required
                                                    />
                                                    <div className="invalid-feedback">
                                                        Price is required
                                                    </div>
                                                </div>
                                            </div>

                                            <hr className="my-4" />

                                            <button
                                                className="w-100 btn btn-primary "
                                                type="submit"
                                            >
                                                Add
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                {message ?
                                    <div className="col-md-5 col-lg-4 order-md-last">
                                        <div className="card mb-4">
                                            <div className="card-header py-3 bg-light">
                                                <h5 className="mb-0">Esit:</h5>
                                            </div>
                                            <div className="card-body">
                                                <div className="message">{message ? <p>{message}</p> : null}</div>
                                            </div>
                                        </div>
                                    </div>
                                    : null}
                            </div>
                        </div>
                    </div>
                </div>
            </>);
    }

    return (
        <>
            <Navbar />
            <ProductForm />
            <Footer />
        </>
    );
};

export default Newproduct;