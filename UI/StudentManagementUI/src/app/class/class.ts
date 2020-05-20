import { Semester } from '../semester/semester';

export class Class {
    id: number;
    name: string;
    id_semester: number;
    isDeleted: boolean;
    semester: Semester;
}