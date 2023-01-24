import { Footer, Navbar } from "../components";
import React, { useState, useEffect } from "react";

const Purchases = () => {
  let componentMounted = true;
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);

  useEffect(() => {
    const getPurchases = async () => {
      setLoading(true);
      const response = await fetch("http://localhost:8081/egames/purchase/all");
      if (componentMounted) {
        setData(await response.clone().json());
        setLoading(false);
      }

      return () => {
        componentMounted = false;
      };
    };

    getPurchases();
  }, []);


  const Loading = () => {
    return (
      <>
        <div className="col-12 py-5 text-center">
          Loading
        </div>
      </>
    );
  };

  const ShowPurchases = () => {
    return (
      <>
        <div className="container py-12">
          <div className="row my-2">
            <div className="card-header py-3">
              <h4 className="mb-0">Purchases</h4>
            </div>
            {data.map((purchaseReceipt, j) => {
              return (
                <div className="card" key={j}>
                  <div className="card-body">
                    <div className="row ">
                      <div className="card-header" >
                        Customer: {purchaseReceipt.purchase.customer.email} - Address: {purchaseReceipt.purchase.address.line}, {purchaseReceipt.purchase.address.state}, {purchaseReceipt.purchase.address.zipCode}
                        <div className="col-sm-12 my-1">
                          Purchase date: {purchaseReceipt.purchase.purchaseDate} - Status: {purchaseReceipt.purchase.status.name}
                        </div>
                      </div>
                      <div className="col-12 my-1">
                        Products: {purchaseReceipt.purchasedVideogameList.map((item, i) => <li key={i}>{item.videogame.title} - {item.videogame.price}€ X {item.quantity}</li>)}
                        <li >Shipping: 6€</li>
                        Total: {purchaseReceipt.purchase.total}€
                      </div>
                    </div>
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
      {loading ? <Loading /> : <ShowPurchases />}
      <Footer />
    </>
  );
}
export default Purchases