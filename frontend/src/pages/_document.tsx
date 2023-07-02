import Document, { Head, Main, NextScript, DocumentContext, Html } from 'next/document'

export default class CustomDocument extends Document {
  static async getInitialProps(ctx: DocumentContext) {
    const firstRenderPage = ctx.renderPage;
    ctx.renderPage = () =>
      firstRenderPage({
        // eslint-disable-next-line react/display-name
        enhanceApp: App => props => <App {...props} />
      });
    const initialProps = await Document.getInitialProps(ctx)
    return initialProps
  }

  render() {
    return (
      <Html lang="en">
        <Head>
        </Head>
        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    )
  }
}