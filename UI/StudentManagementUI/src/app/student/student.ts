import { Semester } from '../semester/semester';
import { Class } from '../class/class';

export class Student {
    id: number;
    name: string;
    birthDate: string;
    gender: string;
    studentCode: number;
    id_class: number;
    name_class: string;
    isDeleted: boolean;
    class: Class;
}