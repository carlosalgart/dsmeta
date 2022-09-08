import NotificationButton from "./components/NotificationButton";
import Header from "./components/Header";
import SalesCard from "./components/SalesCard";
function App() {
  return (
    <>
      <h1>Meu primeiro projeto de Frontimento End</h1>
      <Header />
      <main>
      <section id="sales">
        <div className="dsmeta-container">
            <SalesCard />
        </div>
      </section>
    </main>
    </>
  )
}
export default App;
