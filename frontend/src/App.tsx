import NotificationButton from "./components/NotificationButton"
import Header from "./components/Header"
import SalesCard from "./components/SalesCard"
function App() {
  return (
    <>
      <h1>The first</h1>
      <Header />
      <main>
      <section id="sales">
        <div class="dsmeta-container">
            <SalesCard />
        </div>
      </section>
    </main>
    </>
  )
}
export default App
