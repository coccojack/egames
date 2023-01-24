import React from "react";
import { Footer, Navbar } from "../components";
import { useSelector } from "react-redux";
import { useNavigate } from 'react-router-dom';
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { delCart } from "../redux/action";
const Checkout = () => {
  const state = useSelector((state) => state.handleCart);
  let componentMounted = true;
  const [address, setAddress] = useState("");
  const [loading, setLoading] = useState(false);
  const [trigger, setTrigger] = useState(true);
  const customerid = 2;
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    const loadAddress = async () => {
      setLoading(true);
      if (componentMounted) {
        const response = await fetch(`http://localhost:8081/egames/address/getByCustomerId?customerId=` + customerid, {
          method: 'GET',
        });
        const data = await response.json();
        if (data != null) {
          setAddress(data[0]);
        }
        setLoading(false);
      }
      return () => {
        componentMounted = false;

      };
    };
    loadAddress();
  }, []);

  const clearCart = (products) => {
    products.map((product) => {
      for (let i = 0; i < product.qty; i++) {
        dispatch(delCart(product));
      }
    })
  };

  const handlePurchase = async (e, total, address) => {
    e.preventDefault();
    if (trigger) {
      try {
        var videogameMap = new Map();
        state.map((item) => {
          videogameMap.set(item.id, item.qty)
        });
        let jsonMap = JSON.stringify(Object.fromEntries(videogameMap));
        let res = await fetch("http://localhost:8081/egames/purchase/add", {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
          },
          method: "POST",
          body: JSON.stringify({
            customerId: customerid,
            addressId: address.id,
            total: total,
            purchaseList: jsonMap
          })
        });
        let resJson = await res.json();
        if (res.status === 200) {
          clearCart(state);
          navigate("/");
        } else {
          console.log("error")
        }
        setTrigger(false);
      } catch (err) { }
    } else return null;
  }



  const EmptyCart = () => {
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-12 py-5 bg-light text-center">
            <h4 className="p-3 display-5">No item in Cart</h4>
            <Link to="/" className="btn btn-outline-dark mx-4">
              <i className="fa fa-arrow-left"></i> Continue Shopping
            </Link>
          </div>
        </div>
      </div>
    );
  };




  const ShowCheckout = () => {
    let subtotal = 0;
    let shipping = 6.0;
    let totalItems = 0;
    state.map((item) => {
      return (subtotal += item.price * item.qty);
    });

    state.map((item) => {
      return (totalItems += item.qty);
    });
    return (
      <>
        <div className="container py-5">
          <div className="row my-4">
            <div className="col-md-5 col-lg-4 order-md-last">
              <div className="card mb-4">
                <div className="card-header py-3 bg-light">
                  <h5 className="mb-0">Order Summary</h5>
                </div>
                <div className="card-body">
                  <ul className="list-group list-group-flush">
                    <li className="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                      Products ({totalItems})<span>{Math.round(subtotal)}€</span>
                    </li>
                    <li className="list-group-item d-flex justify-content-between align-items-center px-0">
                      Shipping
                      <span>{shipping}€</span>
                    </li>
                    <li className="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                      <div>
                        <strong>Total amount</strong>
                      </div>
                      <span>
                        <strong>{Math.round(subtotal + shipping)}€</strong>
                      </span>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-md-7 col-lg-8">
              <div className="card mb-4">
                <div className="card-header py-3">
                  <h4 className="mb-0">Billing address</h4>
                </div>
                <div className="card-body">
                  <form className="needs-validation" onSubmit={(e) => handlePurchase(e, Math.round(subtotal + shipping), address)} >
                    <div className="row g-3">
                      <div className="col-sm-6 my-1">
                        <label for="firstName" className="form-label">
                          First name
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="firstName"
                          placeholder=""
                          value={address ? address.customer.name : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Valid first name is required.
                        </div>
                      </div>

                      <div className="col-sm-6 my-1">
                        <label for="lastName" className="form-label">
                          Last name
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="lastName"
                          placeholder=""
                          value={address ? address.customer.surname : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Valid last name is required.
                        </div>
                      </div>

                      <div className="col-12 my-1">
                        <label for="email" className="form-label">
                          Email
                        </label>
                        <input
                          type="email"
                          className="form-control"
                          id="email"
                          placeholder="you@example.com"
                          value={address ? address.customer.email : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Please enter a valid email address for shipping
                          updates.
                        </div>
                      </div>

                      <div className="col-12 my-1">
                        <label for="address" className="form-label">
                          Address
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="address"
                          placeholder="1234 Main St"
                          value={address ? address.line : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Please enter your shipping address.
                        </div>
                      </div>

                      <div className="col-md-5 my-1">
                        <label for="country" className="form-label">
                          Country
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="country"
                          placeholder="Country"
                          value={address ? address.country : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Please enter your country.
                        </div>
                      </div>

                      <div className="col-md-4 my-1">
                        <label for="state" className="form-label">
                          State
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="country"
                          placeholder="State"
                          value={address ? address.state : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Please enter your state.
                        </div>
                      </div>

                      <div className="col-md-3 my-1">
                        <label for="zipCode" className="form-label">
                          Zip Code
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="zipCode"
                          placeholder="1234 Main St"
                          value={address ? address.zipCode : ""}
                          required
                          disabled
                        />
                        <div className="invalid-feedback">
                          Please enter your zip code.
                        </div>
                      </div>
                    </div>

                    <hr className="my-4" />
                    <button
                      className="w-100 btn btn-primary "
                      type="submit"
                    >
                      Purchase
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  };
  return (
    <>
      <Navbar />
      <div className="container my-3 py-3">
        <h1 className="text-center">Purchase</h1>
        <hr />
        {state.length ? <ShowCheckout /> : <EmptyCart />}
      </div>
      <Footer />
    </>
  );
};

export default Checkout;
