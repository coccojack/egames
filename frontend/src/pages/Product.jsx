import React, { useEffect, useState } from "react";
import Skeleton from "react-loading-skeleton";
import { Link, useParams } from "react-router-dom";
import Marquee from "react-fast-marquee";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";

import { Footer, Navbar } from "../components";

const Product = () => {
  const { id } = useParams();
  const [product, setProduct] = useState([]);
  const [similarProducts, setSimilarProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [loading2, setLoading2] = useState(false);
  const urlNoImage = '../assets/no_image.jpeg';
  const [productImage, setProductImage] = useState(urlNoImage);
  const dispatch = useDispatch();

  const addProduct = (product) => {
    dispatch(addCart(product));
  };

  useEffect(() => {
    const getProduct = async () => {
      setLoading(true);
      setLoading2(true);
      const response = await fetch(`http://localhost:8081/egames/videogame/id/${id}`, {
        method: 'GET',
      });
      const data = await response.json();
      if (data.image != null) {
        setProductImage(`data:image/jpeg;base64,${data.image}`)
      }
      else {
        setProductImage(urlNoImage);
      }
      setProduct(data);
      setLoading(false);
      const response2 = await fetch(
        `http://localhost:8081/egames/videogame/related/${id}`, {
        method: 'GET',
      }
      );
      const data2 = await response2.json().catch((error) => { });
      if (data2 != null) {
        console.log(data2)
        setSimilarProducts(data2);
        for (let i = 0; i < data2.length; i++) {
          if (data2[i].image != null) {
            data2[i].image = `data:image/jpeg;base64,${data2[i].image}`
          }
          else {
            data2[i].image = urlNoImage;
          }
        }
      }
      setLoading2(false);
    };
    getProduct();
  }, [id]);

  const Loading = () => {
    return (
      <>
        <div className="container my-5 py-2">
          <div className="row">
            <div className="col-md-6 py-3">
              <Skeleton height={400} width={400} />
            </div>
            <div className="col-md-6 py-5">
              <Skeleton height={30} width={250} />
              <Skeleton height={90} />
              <Skeleton height={40} width={70} />
              <Skeleton height={50} width={110} />
              <Skeleton height={120} />
              <Skeleton height={40} width={110} inline={true} />
              <Skeleton className="mx-3" height={40} width={110} />
            </div>
          </div>
        </div>
      </>
    );
  };

  const ShowProduct = () => {
    return (
      <>
        <div className="container my-5 py-2">
          <div className="row">
            <div className="col-md-6 col-sm-12 py-3">
              <img
                className="img-fluid"
                src={productImage}
                alt={product.title}
                width="400px"
                height="400px"
              />
            </div>
            <div className="col-md-6 col-md-6 py-5">
              <h4 className="text-uppercase text-muted">{product.category ? product.category : null}</h4>
              <h1 className="display-5">{product.title ? product.title : null}</h1>
              <h3 className="display-6  my-4">{product.price ? product.price : null}€</h3>
              <p className="lead"><b>Description:</b> {product.description ? product.description : null}</p>
              <p className="lead"><b>Release Date:</b> {product.releaseDate ? product.releaseDate : null}</p>
              <p className="lead"><b>Genre:</b>  {product.genre ? product.genre.name : null}</p>
              <p className="lead"><b>Platform:</b>  {product.platform ? product.platform.name : null}</p>
              <p className="lead"><b>Pegi:</b>  {product.pegi ? product.pegi : null}</p>
              <p className="lead"> <b>Game Modes: </b> {product.gameModeSet ? product.gameModeSet.map((item, i) => <li key={i}>{item.name} - Internet required: {item.internetRequired == true ? "yes" : "no"}</li>) : null}</p>
              <p className="lead"> <b>Themes:</b>  {product.theme ? product.theme.map((item, i) => <li key={i}>{item.name}</li>) : null}</p>
              <p className="lead"> <b>Languages:</b>  {product.languageSet ? product.languageSet.map((item, i) => <li key={i}>{item.name}</li>) : null}</p>
              <p className="lead"> <b>Input Types: </b> {product.inputTypeSet ? product.inputTypeSet.map((item, i) => <li key={i}>{item.name}</li>) : null}</p>
              <p className="lead"><b>Minimum player number:</b>  {product.playerNum ? product.playerNum : null}</p>
              <p className="lead"><b>Software House: </b> {product.softwareHouse ? product.softwareHouse.name : null}</p>
              <p className="lead"><b>Audio Dev:</b>  {product.audioDev ? product.audioDev.name : null} {product.audioDev ? product.audioDev.surname : null}</p>
              <p className="lead"><b>Graph Dev: </b> {product.graphDev ? product.graphDev.name : null} {product.graphDev ? product.graphDev.surname : null}</p>
              <p className="lead"><b>Game Dev:</b>  {product.gameDev ? product.gameDev.name : null} {product.gameDev ? product.gameDev.surname : null}</p>
              <p className="lead"><b>Adult Game: </b> {product.adultGame == true ? "yes" : "no"}</p>
              <p className="lead"><b>In-game purchases: </b> {product.ingamePurchases == true ? "yes" : "no"}</p>
              <button
                className="btn btn-outline-dark"
                onClick={() => addProduct(product)}
              >
                Add to Cart
              </button>
              <Link to="/cart" className="btn btn-dark mx-3">
                Go to Cart
              </Link>
            </div>
          </div>
        </div>
      </>
    );
  };

  const Loading2 = () => {
    return (
      <>
        <div className="my-4 py-4">
          <div className="d-flex">
            <div className="mx-4">
              <Skeleton height={400} width={250} />
            </div>
            <div className="mx-4">
              <Skeleton height={400} width={250} />
            </div>
            <div className="mx-4">
              <Skeleton height={400} width={250} />
            </div>
            <div className="mx-4">
              <Skeleton height={400} width={250} />
            </div>
          </div>
        </div>
      </>
    );
  };

  const ShowSimilarProduct = () => {
    return (
      <>
        <div className="py-4 my-4">
          <div className="d-flex">
            {similarProducts.map((item) => {
              return (
                <div key={item.id} className="card mx-4 text-center">
                  <img
                    className="card-img-top p-3"
                    src={item.image}
                    alt="Card"
                    height={300}
                    width={300}
                  />
                  <div className="card-body">
                    <h5 className="card-title">
                      {item.title.substring(0, 15)}{item.title.length > 15 ? '...' : null}
                    </h5>
                  </div>
                  {/* <ul className="list-group list-group-flush">
                    <li className="list-group-item lead">${product.price}</li>
                  </ul> */}
                  <div className="card-body">
                    <Link
                      to={"/product/" + item.id}
                      className="btn btn-dark m-1"
                    >
                      Buy Now
                    </Link>
                    <button
                      className="btn btn-dark m-1"
                      onClick={() => addProduct(item)}
                    >
                      Add to Cart
                    </button>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      </>
    );
  };
  return (
    <>
      <Navbar />
      <div className="container">
        <div className="row">
          {loading ? <Loading /> : <ShowProduct />}
        </div>
        <div className="row my-5 py-5">
          <div className="d-none d-md-block">
            <h2 className="">You may also Like</h2>
            <Marquee
              pauseOnHover={true}
              pauseOnClick={true}
              speed={50}
            >
              {loading2 ? <Loading2 /> : <ShowSimilarProduct />}
            </Marquee>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Product;
