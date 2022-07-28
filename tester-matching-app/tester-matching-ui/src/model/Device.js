export class Device {
    constructor(id, description) {
        this._id = id;
        this._description = description;
    }


    get id() {
        return this._id;
    }

    set id(value) {
        this._id = value;
    }

    get description() {
        return this._description;
    }

    set description(value) {
        this._description = value;
    }

    toString() {
        return "ID: " + this.id + " Description: " + this.description;
    }
}