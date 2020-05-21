import { Semester } from '../semester/semester';
import { Class } from '../class/class';

export class Student {
    id: number;
    name: string;
    birthDate: Date;
    gender: string;
    studentCode: number;
    id_class: number;
    isDeleted: boolean;
    class: Class;
}