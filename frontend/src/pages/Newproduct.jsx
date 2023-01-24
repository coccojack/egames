import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { Footer, Navbar } from "../components";
import { InputLabel, Select, MenuItem } from '@mui/material';
const Newproduct = () => {

    const [message, setMessage] = useState("");
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [loading2, setLoading2] = useState(false);
    const [platfomList, setPlatformList] = useState([]);
    const [genreList, setGenreList] = useState([]);
    let title, description, platformName, genre, price, stock;
    let componentMounted = true;

    useEffect(() => {
        const getPlatforms = async () => {
            setLoading(true);
            setLoading2(true);
            if (componentMounted) {
                const response = await fetch("http://localhost:8081/egames/platform/all")
                    .catch(exception => { });
                const data2 = await response.json().catch((error) => { });
                if (data2 != null) {
                    setPlatformList(data2);
                }
                const response2 = await fetch("http://localhost:8081/egames/genre/all")
                    .catch(exception => { });
                const data3 = await response2.json().catch((error) => { });
                if (data3 != null) {
                    setGenreList(data3);
                }
                setLoading(false);
                setLoading2(false);
            }
            return () => {
                componentMounted = false;
            };
        };
        getPlatforms();
    }, []);

    const handlePlatformChange = (event => {
        document.getElementById("platform-name").value = event.target.value;
        console.log(document.getElementById("platform-name").value)
    });

    const handleGenreChange = (event => {
        document.getElementById("genre-name").value = event.target.value;
        console.log(document.getElementById("genre-name").value)
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        title = document.getElementById("title").value;
        description = document.getElementById("description").value;
        genre = document.getElementById("genre-name").value;
        platformName = document.getElementById("platform-name").value;
        price = document.getElementById("price").value;
        stock = document.getElementById("stock-quantity").value;
        console.log("Sending data: title: " + title, " |description: " + description + " |genre" + genre + " |platformName" + platformName + " |price" + price)
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


    const Platform = () => {
        return (
            <>
                <div>
                    <InputLabel id="platform-label">Platform</InputLabel>
                    <Select
                        labelId="platform-label"
                        id="platform-name"
                        default="Select platform"
                        onChange={handlePlatformChange}
                    >
                        {platfomList.map((plat,i) => {
                            return <MenuItem value={plat.name} key={i}>{plat.name}</MenuItem>
                        })}
                    </Select>
                    <div className="invalid-feedback">
                        Please enter a valid platform name.
                    </div>
                </div>
            </>
        );
    };

    const Genre = () => {
        return (
            <>
                <div>
                    <InputLabel id="genre-label">Genre</InputLabel>
                    <Select
                        labelId="genre-label"
                        id="genre-name"
                        default="Select Genre"
                        onChange={handleGenreChange}
                    >
                        {genreList.map((gen,i) => {
                            return <MenuItem value={gen.name} key={i}>{gen.name}</MenuItem>
                        })}
                    </Select>
                    <div className="invalid-feedback">
                        Please enter a valid genre name.
                    </div>
                </div>
            </>
        );
    };


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
                                                    {loading ? null : <Platform />}
                                                </div>
                                                <div className="col-6 my-1">
                                                    {loading2 ? null : <Genre />}
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