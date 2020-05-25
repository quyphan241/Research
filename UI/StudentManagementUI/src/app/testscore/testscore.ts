import { Student } from '../student/student';
import { Class } from '../class/class';

export class TestScore {
    id: number;
    firstScore: number;
    secondScore: number;
    finalScore: number;
    id_subject: number;
    id_student: number;
    isDeleted: boolean;
    name_student: string;
    name_class: string;
    student: Student;
    class: Class;
}