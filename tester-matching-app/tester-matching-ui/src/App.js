import './App.css';
import Table from "../src/components/Table/Table";
import Sidebar from "./components/Sidebar/Sidebar";
import React, {useEffect, useState} from 'react';
import {getAllDevices} from "./services/DeviceService"
import {getAllCountries} from "./services/CountryService"

function App() {
    const [countryNames, setCountryNames] = useState([]);

    const [deviceIds, setDeviceIds] = useState([]);
    const [deviceDescriptions, setDeviceDescriptions] = useState([]);


    useEffect(() => {
        getAllDevices()
            .then(res => {
                const devId = [];
                const devDes = [];
                res.forEach(device => {
                    devId.push(device.id);
                    devDes.push(device.description);
                });
                setDeviceIds(devId);
                setDeviceDescriptions(devDes);
            });
    }, []);

    useEffect(() => {
        getAllCountries()
            .then(res => {
                setCountryNames(res);
            });
    }, []);

    return (
        <div className="App">
            <Sidebar countries={countryNames} deviceKeys={deviceIds} devices={deviceDescriptions}></Sidebar>
            {/*<Table></Table>*/}
        </div>
    );
}

export default App;
