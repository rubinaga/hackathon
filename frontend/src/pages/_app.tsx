import React, { Fragment } from 'react';
import { wrapper } from '@utils/store';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from "react-toastify";
import "@styles/globals.scss"
import "@fortawesome/fontawesome-svg-core/styles.css";
import { config } from "@fortawesome/fontawesome-svg-core";
import type { AppProps } from 'next/app';
import Loader from '@components/loader/loader';

config.autoAddCss = false;

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <Fragment>
      <Loader />
      <ToastContainer
        toastStyle={{ backgroundColor: "yellowgreen", color: "white" }}
        position="top-center"
        autoClose={5000}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        draggable
      />
      <Component {...pageProps} />
    </Fragment>
  )
}

export default wrapper.withRedux(MyApp);