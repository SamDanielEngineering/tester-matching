function Dropdown(props) {

    const values = [];

    for (let i = 0; i < props.keys.length; i++) {
        const key = props.keys[i];
        const value = props.values[i];
        values.push(
            <div className="mb-3">
                <div className="form-check">
                    <input type="checkbox" className="form-check-input" id="dropdownCheck2"/>
                    <label className="form-check-label" htmlFor="dropdownCheck2">
                        {value}
                    </label>
                </div>
            </div>
        )
    }


    return (
        <div className="dropdown">
            <button type="button" className="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown"
                    aria-expanded="false" data-bs-auto-close="outside">
                {props.title}
            </button>
            <form className="dropdown-menu p-4">
                <div className="mb-3">
                    <div className="form-check">
                        <input type="checkbox" className="form-check-input" id="dropdownCheck2"/>
                        <label className="form-check-label" htmlFor="dropdownCheck2">
                            All
                        </label>
                    </div>
                </div>
                {values}
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    )
}

export default Dropdown;
