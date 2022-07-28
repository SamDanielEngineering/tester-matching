import './Sidebar.css';
import React, {useEffect, useState} from "react";
import {AllCheckerCheckbox, Checkbox, CheckboxGroup} from '@createnl/grouped-checkboxes';
import {getTesters} from "../../services/TesterService";
import Table from "../Table/Table";

export default function Sidebar(props) {

    const [countries, setCountries] = useState([]);
    const [deviceDescriptions, setDeviceDescriptions] = useState([])
    const [checkedCountries, setCheckedCountries] = useState([]);
    const [checkedDeviceIds, setCheckedDeviceIds] = useState([]);
    const [testers, setTesters] = useState([]);

    useEffect(() => {
        const countryData = [];

        for (let i = 0; i < props.countries.length; i++) {
            const country = props.countries[i];
            // countryData.push(
            //     <div className="mb-3">
            //         <div className="form-check">
            //             <label className="form-check-label" htmlFor="dropdownCheck1">
            //                 <Checkbox value={props.countries[i]} className="form-check-input" id="dropdownCheck1"/>
            //                 {country}
            //             </label>
            //         </div>
            //     </div>
            // )
            countryData.push(
                <li className="list-group-item" id="data-group">
                    <Checkbox value={props.countries[i]} className="form-check-input" id="countries"/>
                    {country}
                </li>
            )
        }

        setCountries(countryData);
    }, [props]);

    useEffect(() => {
        const deviceData = [];

        for (let i = 0; i < props.devices.length; i++) {
            const deviceDescription = props.devices[i];
            // deviceData.push(
            //     <div className="mb-3">
            //         <div className="form-check">
            //             <label className="form-check-label" htmlFor="dropdownCheck2">
            //                 <Checkbox value={props.deviceKeys[i]} className="form-check-input" id="dropdownCheck2"/>
            //                 {deviceDescription}
            //             </label>
            //         </div>
            //     </div>
            // )
            deviceData.push(
                <li className="list-group-item" id="data-group">
                    <Checkbox value={props.deviceKeys[i]} className="form-check-input" id="devices"/>
                    {deviceDescription}
                </li>
            )
        }

        setDeviceDescriptions(deviceData);
    }, [props])

    useEffect(() => {
        const countryData = props.countries;
        const deviceData = props.deviceKeys;
        const testersData = getTesters(deviceData, countryData);

        testersData.then(data => {
            const dataArr = [];
            data.forEach(tester => dataArr.push(tester));
            setTesters(dataArr);
        });

    }, [props])

    const onCheckboxChangeCountries = (checkboxes) => {
        setCheckedCountries(checkboxes
            .filter(checkbox => checkbox.id === "countries" && checkbox.checked === true)
            .map(checked => checked.value));
    }

    const onCheckboxChangeDevices = (checkboxes) => {
        setCheckedDeviceIds(checkboxes
            .filter(checkbox => checkbox.id === "devices" && checkbox.checked === true)
            .map(checked => checked.value));
    }

    function printCheckboxes() {
        const testersData = getTesters(checkedDeviceIds, checkedCountries);

        testersData.then(data => {
            const dataArr = [];
            data.forEach(tester => dataArr.push(tester));
            setTesters(dataArr);
        });
    }


    return (
        <form id="sidebarMenu" className="collapse d-lg-block sidebar bg-white">
            <div className="position-sticky" id="sidebar-filters">
                <div className="list-group list-group-flush mx-3 mt-4">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">Countries</h5>
                            <CheckboxGroup defaultChecked onChange={onCheckboxChangeCountries}>
                                <li className="list-group-item">
                                    <AllCheckerCheckbox className="form-check-input" id="countries"/>
                                    All
                                </li>
                                <ul className="list-group list-group-flush">
                                    {countries}
                                </ul>
                            </CheckboxGroup>
                        </div>
                    </div>
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">Devices</h5>
                            <CheckboxGroup defaultChecked onChange={onCheckboxChangeDevices}>
                                <li className="list-group-item">
                                    <AllCheckerCheckbox className="form-check-input" id="devices"/>
                                    All
                                </li>
                                <ul className="list-group list-group-flush">
                                    {deviceDescriptions}
                                </ul>
                            </CheckboxGroup>
                        </div>
                    </div>
                    <button className="btn btn-success" type="button"
                            data-bs-target="#collapseExample" aria-expanded="false"
                            aria-controls="collapseExample"
                            onClick={() => printCheckboxes()}>
                        Apply
                    </button>
                </div>
            </div>
            <Table data={testers}></Table>
        </form>
    );

    // return (
    //     <form id="sidebarMenu" className="collapse d-lg-block sidebar collapse bg-white">
    //         <div className="position-sticky">
    //             <div className="list-group list-group-flush mx-3 mt-4">
    //                 <button className="btn btn-outline-dark" type="button" data-bs-toggle="collapse"
    //                         data-bs-target="#collapseCountries" aria-expanded="false"
    //                         aria-controls="collapseCountries">
    //                     Countries
    //                 </button>
    //                 <div className="collapse" id="collapseCountries">
    //                     <div className="mb-3">
    //                         <div className="form-check">
    //                             <CheckboxGroup defaultChecked onChange={onCheckboxChangeCountries}>
    //                                 <label className="form-check-label" htmlFor="dropdownCheck1">
    //                                     <AllCheckerCheckbox className="form-check-input" id="dropdownCheck1"/>
    //                                     All
    //                                 </label>
    //                                 {countries}
    //                             </CheckboxGroup>
    //                         </div>
    //                     </div>
    //                 </div>
    //                 <button className="btn btn-outline-dark" type="button" data-bs-toggle="collapse"
    //                         data-bs-target="#collapseDevices" aria-expanded="false"
    //                         aria-controls="collapseDevices">
    //                     Devices
    //                 </button>
    //                 <div className="collapse" id="collapseDevices">
    //                     <div className="mb-3">
    //                         <div className="form-check">
    //                             <CheckboxGroup defaultChecked onChange={onCheckboxChangeDevices}>
    //                                 <label className="form-check-label" htmlFor="dropdownCheck2">
    //                                     <AllCheckerCheckbox className="form-check-input" id="dropdownCheck2"/>
    //                                     All
    //                                 </label>
    //                                 {deviceDescriptions}
    //                             </CheckboxGroup>
    //                         </div>
    //                     </div>
    //                 </div>
    //                 <button className="btn btn-success" type="button"
    //                         data-bs-target="#collapseExample" aria-expanded="false"
    //                         aria-controls="collapseExample"
    //                         onClick={() => printCheckboxes()}>
    //                     Apply
    //                 </button>
    //             </div>
    //         </div>
    //         <Table data={testers}></Table>
    //     </form>
    // );
}