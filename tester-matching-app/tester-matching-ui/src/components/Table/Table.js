import React, {useEffect, useState} from 'react';
import "./Table.css";


export default function Table(props) {

    const [testers, setTesters] = useState([]);

    useEffect(() => {
        const testerData = [];

        for (let i = 0; i < props.data.length; i++) {

            const name = props.data[i].name;
            const country = props.data[i].country;
            const devices = props.data[i].devices.map(device => device.description);
            const bugCount = props.data[i].bugCount;

            for (let j = 0; j < devices.length - 1; j++) {
                devices[j] = devices[j] + ", ";
            }

            testerData.push(
                <tr>
                    <td>{name}</td>
                    <td>{country}</td>
                    <td>{devices}</td>
                    <td>{bugCount}</td>
                </tr>
            );

        }

        setTesters(testerData);
    },[props])


    return (
        <table className="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Country</th>
                <th scope="col">Devices</th>
                <th scope="col">Bugs</th>
            </tr>
            </thead>
            <tbody>
            {testers}
            </tbody>
        </table>
    )

}